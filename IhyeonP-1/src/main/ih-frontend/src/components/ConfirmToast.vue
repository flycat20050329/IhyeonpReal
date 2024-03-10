<template>
  <div class="content-container">
    {{ text }}
  </div>
  <div class="buttons-container">
    <button class="btn confirm" @click="$emit('click', true)">확인</button>
    <button class="btn cancel" @click="$emit('click', false)">취소</button>
  </div>
</template>

<script>
import { ref } from "vue";

export default {
  name: "ConfirmationModal",
  // 렌더링할 텍스트를 가져옵니다.
  props: {
    text: String,
  },
  setup() {
    // const resolvePromise = ref(null);

    // const confirm = () => {
    //   resolvePromise.value(true);
    // };

    // const cancel = () => {
    //   resolvePromise.value(false);
    // };
    // return { confirm, cancel };
  },
};
</script>

<style>
.content-container {
  color: black;
}
</style>