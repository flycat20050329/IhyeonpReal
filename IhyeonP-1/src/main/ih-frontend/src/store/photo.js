// stores/counter.js
import { defineStore } from 'pinia'

export const usePhotoStore = defineStore('photo', {
  state: () => ({ photos: [] }),
  actions: {
    setPhotos(photoData) {
      this.photos = photoData;
    }
  },
  getters: {
    doubleCount(state) {
      return state.count * 2
    },
    getPhotos(state) {
      return state.photos;
    }
  }
})

export default usePhotoStore;
