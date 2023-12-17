import axios from 'axios';

const API_URL = 'http://localhost:8080/api/photo/';

class PhotoService {
  async uploadPhotoPost(frm) {
    return axios.post(API_URL + 'uploadPhotoPost', frm, {
      hearder: {
        'Content-Type': 'multipart/form-data'
      }
    })
  }

  async uploadPhoto(frm) {
    return axios.post(API_URL + 'uploadPhoto', frm, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
  }

  getAllPhoto() {
    return axios.get(API_URL + 'getAllPhoto')
  }

  getPhotoPost(id) {
    return axios.get(API_URL + 'getPhotoPost/' + id);
  }

  getPhotoByPostId(id) {
    return axios.get(API_URL + 'getPhotosByPostId/' + id);
  }

  getClickedPhotoData(id) {
    return axios.get(API_URL + 'getClickedPhotoData/' + id);
  }

  editPostText(frm) {
    return axios.post(API_URL + 'updatePostText', frm, {
      hearder: {
        'Content-Type': 'multipart/form-data'
      }
    })
  }

}

export default new PhotoService();
