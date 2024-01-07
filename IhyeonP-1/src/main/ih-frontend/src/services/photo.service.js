import axios from 'axios';

const API_URL = 'http://localhost:8080/api/photo/';

class PhotoService {

  async uploadPhotoPost(frm) {
    return axios.post(API_URL + 'uploadPhotoPost', frm, {
      headers: {
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

  getPostHearts(postId) {
    return axios.get(API_URL + 'getPostHearts/' + postId)
  }

  getReplyByPostId(id) {
    return axios.get(API_URL + 'getReplyByPostId/' + id);
  }

  getPhotoFilteredDate(startDate, endDate) {
    return axios.get(API_URL + 'getPhotoFilteredDate/' + startDate+"/"+endDate)
  }

  editPostText(frm) {
    return axios.post(API_URL + 'updatePostText', frm, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
  }

  async deletePost(frm) {
    return axios.post(API_URL + 'deletePost', frm, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
  }

  deleteReply(frm) {
    return axios.post(API_URL + 'deleteReply', frm, {
      hearders: {
        "Content-Type": "multipart/form-data"
      }
    })
  }

  uploadReply(frm) {
    return axios.post(API_URL + 'uploadReply', frm, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
  }

  favorited(frm) {
    return axios.post(API_URL + 'clickHeart', frm, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
  }

  unFavorited(frm) {
    return axios.post(API_URL + 'deleteHeart', frm, {
      hearders: {
        'Content-Type': 'multipart/form-data'
      }
    })
  }

  ifFavorited(postId, userId) {
    return axios.get(API_URL + 'isFavorited/' + postId + '/' + userId);
  }

}

export default new PhotoService();
