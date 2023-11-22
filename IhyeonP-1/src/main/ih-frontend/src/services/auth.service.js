import axios from 'axios';

const API_URL = 'http://localhost:8080/api/auth/';
const API_URL3 = 'http://localhost:8080/api/info/';

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
    return axios.get(API_URL3 + 'getLunchInfo', {
    });
  }

  getTimeSchedule() {
    return axios.get(API_URL3 + 'getScheduleInfo', {
    });
  }

  getSchoolInfo(location, schoolName) {
    return axios.get(API_URL3 + 'getSchoolInfo', {
      params: {
        loca: location,
        name: schoolName
      }
    });
  }

}

export default new AuthService();
