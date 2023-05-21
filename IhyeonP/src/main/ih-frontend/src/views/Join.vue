<template>
  <div class="wrapper">
    <div id="login">
      <div class="container h-100">
        <div class="row h-100 justify-content-center align-items-center">
          <div class="card">
            <h4 class="card-header">회원가입</h4>
            <div class="card-body">
              <div class="alert alert-danger" role="alert">
                회원가입을 위한 정보를 입력해주시기 바랍니다.
              </div>
              <form data-toggle="validator" role="form" method="post" action="#">

                <!-- 이메일 -->
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
                        <input type="email" class="form-control" placeholder="이메일을 입력해주세요." v-model="userEmail" />
                      </div>
                      <div class="help-block with-errors text-danger"></div>
                    </div>
                  </div>
                </div>

                <!-- 비밀번호 -->
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
                        <input type="password" v-model="userPw" placeholder="비밀번호를 입력해주세요." class="form-control" />
                      </div>
                      <div class="help-block with-errors text-danger"></div>
                    </div>
                  </div>
                </div>

                <!-- 이름 -->
                <!-- <div class="row">
                  <div class="col-md-12">
                    <div class="form-group">
                      <label>Name</label>
                      <div class="input-group">
                        <div class="input-group-prepend">
                          <span class="input-group-text">
                            <i class="fa fa-user" aria-hidden="true" />
                          </span>
                        </div>
                        <input type="text" class="form-control" placeholder="이름을 입력해주세요." v-model="name" />
                      </div>
                      <div class="help-block with-errors text-danger"></div>
                    </div>
                  </div>
                </div> -->

                <!-- 닉네임 -->
                <div class="row">
                  <div class="col-md-12">
                    <div class="form-group">
                      <label>Nickname</label>
                      <div class="input-group">
                        <div class="input-group-prepend">
                          <span class="input-group-text">
                            <i class="fa fa-user" aria-hidden="true" />
                          </span>
                        </div>
                        <input type="text" v-model="userNickName" placeholder="닉네임을 입력해주세요." class="form-control" />
                      </div>
                      <div class="help-block with-errors text-danger"></div>
                    </div>
                  </div>
                </div>

                <!-- 연락처 -->
                <div class="row">
                  <div class="col-md-12">
                    <div class="form-group">
                      <label>Mobile</label>
                      <div class="input-group">
                        <div class="input-group-prepend">
                          <span class="input-group-text">
                            <i class="fa fa-phone" aria-hidden="true" />
                          </span>
                        </div>
                        <input type="text" v-model="userNumber" placeholder="연락처를 입력해주세요('-' 제외)" class="form-control" />
                      </div>
                      <div class="help-block with-errors text-danger"></div>
                    </div>
                  </div>
                </div>

                <!-- 회원가입 버튼 -->
                <div class="row">
                  <div class="col-md-12">
                    <button type="button" class="btn btn-primary btn-lg btn-block" @click="join()">
                      Join
                    </button>
                  </div>
                </div>

              </form>
              <div class="form-group">
                <div class="clear"></div>
                <br>
                <i class="fa fa-user fa-fw" />이미 계정을 등록하셨습니까?
                <a href="/login">로그인</a>
                <br>
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
  data() {
    return {
      form: {
        userEmail: '',
        userPw: '',
        userNickName: '',
        userNumber: '',
      }
    }
  },
  methods: {
    async onSubmit() {
      try {
        await axios.post('/api/join', this.form);
        await this.$router.push({ name: 'Login' })
      } catch (err) {
        throw new Error(err);
      }
    }
  }
}
</script>

<style scoped>
#login {
  margin-top: 50px;
}
</style>