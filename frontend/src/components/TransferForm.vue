<template>
  <div class="card">
    <h2>Agendar Transferência</h2>

    <form @submit.prevent="onSubmit" class="form">
      <div class="field">
        <label>Conta Origem</label>
        <input v-model="form.accountOrigin" type="text" maxlength="10" required />
      </div>

      <div class="field">
        <label>Conta Destino</label>
        <input v-model="form.accountDestiny" type="text" maxlength="10" required />
      </div>

      <div class="field">
        <label>Valor (R$)</label>
        <input v-model.number="form.valueTransfer" type="number" step="0.01" min="0" required />
      </div>

      <div class="field">
        <label>Data da Transferência</label>
        <input v-model="form.scheduleTransferDate" type="date" required />
      </div>

      <button type="submit" :disabled="loading">
        {{ loading ? "Agendando..." : "Agendar" }}
      </button>

      <p v-if="apiError" class="error">{{ apiError }}</p>
      <p v-if="successMessage" class="success">{{ successMessage }}</p>
    </form>
  </div>
</template>

<script setup>
    import { reactive, ref } from "vue"
    import axios from "axios"

    const emit = defineEmits(["created"])

    const api = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL || "http://localhost:8080",
    headers: { "Content-Type": "application/json" },
    })

    const form = reactive({
    accountOrigin: "",
    accountDestiny: "",
    valueTransfer: null,
    scheduleTransferDate: "",
    })

    const successMessage = ref("")
    const apiError = ref("")
    const loading = ref(false)

    async function onSubmit() {
    try {
        loading.value = true
        apiError.value = ""
        successMessage.value = ""

        const payload = {
        accountOrigin: form.accountOrigin,
        accountDestiny: form.accountDestiny,
        valueTransfer: Number(form.valueTransfer),
        scheduleTransferDate: new Date(form.scheduleTransferDate).toISOString(),
        }

        await api.post("/bank-transfers", payload)

        successMessage.value = "Transferência agendada com sucesso!"
        emit("created") 

        form.accountOrigin = ""
        form.accountDestiny = ""
        form.valueTransfer = null
        form.scheduleTransferDate = ""
    } catch (err) {
        apiError.value = err?.response?.data?.message || "Erro ao agendar transferência"
    } finally {
        loading.value = false
    }
    }
</script>


<style scoped>
    .card {
        max-width: 520px;
        margin: 24px auto;
        padding: 24px;
        border: 1px solid #eee;
        border-radius: 16px;
        box-shadow: 0 4px 18px rgba(0,0,0,0.04);
        background: #fff;
    }
    .title {
        margin: 0 0 16px;
        font-size: 20px;
        font-weight: 700;
    }
    .form {
        display: grid;
        gap: 14px;
    }
    .field {
        display: grid;
        gap: 6px;
    }
    label {
        font-size: 14px;
        font-weight: 600;
    }
    input[type="text"],
    input[type="number"],
    input[type="date"] {
        appearance: none;
        width: 100%;
        padding: 10px 12px;
        font-size: 14px;
        border: 1px solid #ddd;
        border-radius: 10px;
        outline: none;
        transition: border-color 0.15s ease;
    }
    input:focus {
        border-color: #6b9cff;
    }
    
    .invalid {
        border-color: #e22 !important;
    }
    
    .hint {
        color: #777;
        font-size: 12px;
    }

    .actions {
        display: flex;
        gap: 10px;
        margin-top: 8px;
    }
    button {
        padding: 10px 16px;
        border-radius: 10px;
        border: none;
        cursor: pointer;
        font-weight: 600;
    }
    button[disabled] {
        opacity: 0.65;
        cursor: not-allowed;
    }
    button.ghost {
        background: transparent;
        border: 1px solid #ddd;
    }
    .banner {
        margin-top: 12px;
        padding: 10px 12px;
        border-radius: 8px;
        font-size: 14px;
    }
    .error-banner {
        background: #ffe8e8;
        color: #a30000;
        border: 1px solid #ffc7c7;
    }
    .success-banner {
        background: #e8fff0;
        color: #006b2e;
        border: 1px solid #c7ffd7;
    }
    .error {
    color: #e22;
    font-size: 12px;
    }
</style>