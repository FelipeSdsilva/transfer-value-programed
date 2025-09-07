<template>
  <div class="card">
    <h2>Extrato de Transferências</h2>

    <table v-if="items.length > 0" class="table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Conta Origem</th>
          <th>Conta Destino</th>
          <th>Valor</th>
          <th>Taxa</th>
          <th>Data Agendada</th>
          <th>Data Transferência</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="t in items" :key="t.id">
          <td>{{ t.id }}</td>
          <td>{{ t.accountOrigin }}</td>
          <td>{{ t.accountDestiny }}</td>
          <td>{{ formatCurrency(t.valueTransfer) }}</td>
          <td>{{ formatCurrency(t.payTax) }}</td>
          <td>{{ formatDate(t.scheduleTransferDate) }}</td>
          <td>{{ formatDate(t.transferDate) }}</td>
        </tr>
      </tbody>
    </table>

    <p v-else class="empty">Nenhuma transferência encontrada.</p>
    <p v-if="apiError" class="error">{{ apiError }}</p>
  </div>
</template>

<script setup>
  import { ref, onMounted } from "vue"
  import axios from "axios"

  const api = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL || "http://localhost:8080",
    headers: { "Content-Type": "application/json" },
  })

  const items = ref([])
  const page = ref(0)
  const size = ref(10)
  const totalPages = ref(0)
  const apiError = ref("")

  function formatCurrency(value) {
    return new Intl.NumberFormat("pt-BR", { style: "currency", currency: "BRL" }).format(value || 0)
  }

  function formatDate(isoDate) {
    if (!isoDate) return ""
    return new Date(isoDate).toLocaleDateString("pt-BR")
  }

  async function loadData() {
    try {
      apiError.value = ""
      const { data } = await api.get("/bank-transfers", {
        params: { page: page.value, size: size.value },
      })
      items.value = data.content || []
      totalPages.value = data.totalPages || 0
    } catch (err) {
      apiError.value = err?.response?.data?.message || "Erro ao carregar transferências"
    }
  }

  onMounted(() => {
    loadData()
  })


  defineExpose({ loadData })
</script>

<style scoped>
  .card {
    max-width: 900px;
    margin: 24px auto;
    padding: 20px;
    border: 1px solid #eee;
    border-radius: 16px;
    background: #fff;
    box-shadow: 0 4px 18px rgba(0,0,0,0.04);
  }
  .title {
    margin-bottom: 16px;
  }
  .table {
    width: 100%;
    border-collapse: collapse;
  }
  .table th, .table td {
    border: 1px solid #ddd;
    padding: 8px 12px;
    text-align: center;
  }
  .table th {
    background: #f8f8f8;
    font-weight: bold;
  }
  .empty {
    text-align: center;
    margin: 16px 0;
  }
  .pagination {
    margin-top: 16px;
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 12px;
  }
  button {
    padding: 6px 12px;
    border: 1px solid #ddd;
    border-radius: 6px;
    cursor: pointer;
  }
  button:disabled {
    opacity: 0.5;
    cursor: not-allowed;
  }
  .error {
    margin-top: 12px;
    color: #e22;
  }
</style>
