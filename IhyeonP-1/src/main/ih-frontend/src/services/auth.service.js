import axios from 'axios';

const API_URL = 'http://localhost:8080/api/auth/';
const API_URL2 = 'http://localhost:8080/api/lunch/';
const API_URL3 = 'http://localhost:8080/api/schedule/';

class AuthService {
  login(user) {
    return axios
      .post(API_URL + 'signin', {
        username: user.username,
        password: user.password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem('user', JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem('user');
  }

  register(user) {
    return axios.post(API_URL + 'signup', {
      username: user.username,
      email: user.email,
      password: user.password
    });
  }

  please() {
    return axios.get(API_URL + 'aaaaa', {
    });
  };

  getLunchList() {
    return axios.get(API_URL2 + 'getInfo', {
    });
  };

  getTimeSchedule() {
    return axios.get(API_URL3 + 'getInfo', {
    });
  }

  async saveImagePost(frm) {
    return axios.post(API_URL + 'uploadImagePost', frm, {
      hearder: {
        'Content-Type': 'multipart/form-data'
      }
    })
  }

  async saveImage(frm) {
    return axios.post(API_URL + 'uploadImage', frm, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
  }

  getImage() {
    return axios.get(API_URL + 'getImage')
  }

}

export default new AuthService();
