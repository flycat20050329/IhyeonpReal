// stores/counter.js
import { defineStore } from 'pinia'

export const usePhotoStore = defineStore('photo', {
  state: () => ({ photos: [], allPhotos: [] }),
  actions: {
    setPhotos(photoData) {
      this.photos = photoData;
    },
    setAllPhotos(photoData) {
      this.allPhotos = photoData;
    },
    updatePhoto(newValue) {
      this.allPhotos.filter(photo => {
        if (photo.photoPost.id == newValue.id) {
          return newValue;
        } else { return photo }
      })
    },
  },

  getters: {
    getPhotos(state) {
      return state.photos;
    },
    getAllPhotos(state) {
      return state.allPhotos;
    },
  }
})

export default usePhotoStore;
