<template>
  <div class="chatbot">
    <div class="chatbox-messages">
      <div
        v-for="(msg, index) in messages"
        :key="index"
        :class="`chat-message ${msg.sender}`"
      >
        {{ msg.text }}
      </div>
      <div v-if="isLoading" class="chat-message bot">Typing...</div>
    </div>
    <div class="chatbox-input">
      <input
        type="text"
        v-model="userInput"
        @input="handleInputChange"
        placeholder="You can ask questions here"
      />
      <button :disabled="isLoading" @click="handleSendMessage">
        {{ isLoading ? 'Sending...' : 'Send' }}
      </button>
    </div>
  </div>
</template>

<script setup>
import axios from 'axios'
import { ref } from 'vue'
const messages = ref([])
const userInput = ref('')
const isLoading = ref(false)

const handleInputChange = e => {
  userInput.value = e.target.value
}

const handleSendMessage = async () => {
  if (!userInput.value.trim()) return

  const userMessage = { sender: 'user', text: userInput.value }
  messages.value = [...messages.value, userMessage]

  isLoading.value = true

  try {
    const response = await axios.post(
      'https://api.openai.com/v1/chat/completions',
      {
        model: 'gpt-3.5-turbo',
        messages: [{ role: 'user', content: userInput.value }],
      },
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer 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`,
        },
      }
    )

    const botMessage = {
      sender: 'bot',
      text: response.data.choices[0].message.content,
    }
    messages.value = [...messages.value, botMessage]
  } catch (error) {
    const errorMessage = { sender: 'bot', text: 'sorry,Something is wrong.' }
    messages.value = [...messages.value, errorMessage]
  }
  userInput.value = ''
  isLoading.value = false
}
</script>

<style scoped>
.chatbot {
  display: flex;
  flex-direction: column;
  height: 100%;
  background-color: #f9f9f9;
  border: 1px solid #ccc;
  border-radius: 8px;
}

.chatbox-messages {
  flex-grow: 1;
  padding: 10px;
  overflow-y: auto;
  max-height: 300px;

  display: flex;
  flex-direction: column;
}

.chat-message {
  margin-bottom: 10px;
  padding: 8px 10px;
  border-radius: 8px;
}

.chat-message.user {
  background-color: #d1e7dd;
  align-self: flex-end;
  width: auto;
}

.chat-message.bot {
  background-color: #f0f0f0;
  align-self: flex-start;
}

.chatbox-input {
  display: flex;
  padding: 10px;
  border-top: 1px solid #ccc;
}

.chatbox-input input {
  flex-grow: 1;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 8px;
}

.chatbox-input button {
  margin-left: 10px;
  padding: 8px 12px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
}

.chatbox-input button:hover {
  background-color: #0056b3;
}

.chatbox-input button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}
</style>
