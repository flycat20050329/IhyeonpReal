import axios from 'axios';

const API_URL = 'http://localhost:8080/api/photo/';

class PhotoService {
  async uploadImagePost(frm) {
    return axios.post(API_URL + 'uploadImagePost', frm, {
      hearder: {
        'Content-Type': 'multipart/form-data'
      }
    })
  }

  async uploadImage(frm) {
    return axios.post(API_URL + 'uploadImage', frm, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
  }

  getAllImage() {
    return axios.get(API_URL + 'getImage')
  }

  getImagePost(id) {
    return axios.get(API_URL + 'getImagePost/' + id);
  }

  getImageByPostId(id) {
    return axios.get(API_URL + 'getImagesByPostId/' + id);
  }

  getClickedImageData(id) {
    return axios.get(API_URL + 'getClickedImageData/' + id);
  }

}

export default new PhotoService();
