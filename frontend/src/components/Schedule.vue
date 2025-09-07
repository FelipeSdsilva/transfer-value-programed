<template>
  <div>
    <TransferForm @created="refreshList" />
    <TransferList ref="listRef" />
  </div>
</template>

<script setup>
import { ref } from "vue"
import TransferForm from "@/components/TransferForm.vue"
import TransferList from "@/components/TransferList.vue"

const listRef = ref(null)

function refreshList() {
  listRef.value?.loadData()
}
</script>
