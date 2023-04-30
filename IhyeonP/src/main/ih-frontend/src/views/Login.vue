<template>
  <div class="protected" v-if="loginSuccess">
    <h5>로그인 성공!</h5>
  </div>
  <div class="unprotected" v-else-if="loginError">
    <h5>로그인 실패!</h5>
  </div>
  <div class="wrapper">
    <div id="login">
      <div class="container">
        <div class="row justify-content-center align-items-center">
          <div class="col-sm-6">
            <div class="card">
              <h4 class="card-header">로그인</h4>
              <div class="card-body">
                <form data-toggle="validator" role="form" method="post" action="#">
                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <label>Email</label>
                        <div class="input-group">
                          <div class="input-group-prepend">
                            <span class="input-group-text">
                              <i class="fa fa-user" aria-hidden="true" />
                            </span>
                          </div>
                          <input type="text" class="form-control" placeholder="아이디(이메일)를 입력해주세요." v-model="user" />
                        </div>
                        <div class="help-block with-errors text-danger"></div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <label>Password</label>
                        <div class="input-group">
                          <div class="input-group-prepend">
                            <span class="input-group-text">
                              <i class="fa fa-lock" aria-hidden="true" />
                            </span>
                          </div>
                          <input type="password" v-model="password" placeholder="비밀번호를 입력해주세요." class="form-control" />
                        </div>
                        <div class="help-block with-errors text-danger"></div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-12">
                      <button type="button" class="btn btn-primary btn-lg btn-block" @click="login()">
                        Login
                      </button>
                    </div>
                  </div>
                </form>
                <div class="form-group">
                  <div class="clear"></div>
                  <br>
                  <i class="fa fa-user fa-fw" />처음 방문하십니까?
                  <a href="/join">회원가입</a>
                  <br>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from 'axios';

export default {
  name: 'login',
  data() {
    return {
      loginSuccess: false,
      loginError: false,
      user: '',
      password: '',
      error: false
    }
  },
  methods: {
    async login() {
      const me = this;
      try {
        const result = await axios.get('/v1/login', {
          auth: {
            username: this.user,
            password: this.password
          }
        });
        if (result.status === 200) {
          this.loginSuccess = true;
        }
      } catch (err) {
        this.loginSuccess = false;
        this.loginError = true;
        throw new Error(err)
      }
    }
  }
}
</script>
<style scoped>
#login {
  margin-top: 150px;
}
</style>