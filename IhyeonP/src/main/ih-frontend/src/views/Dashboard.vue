<template>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <div>
    <full-page :options="options">
      <div class="section">
        <div class="container text-center">
          <div class="headerButtons">
            <div class="col-2">
              <a class="btn btn-outline-primary" href="#" role="button">
                <img src="https://www.google.com/imgres?imgurl=https%3A%2F%2Flookaside.fbsbx.com%2Flookaside%2Fcrawler%2Fmedia%2F%3Fmedia_id%3D809880019152627&tbnid=iYUUiMqeQpEUAM&
                            vet=12ahUKEwjO9a38yPj9AhXVRN4KHS88DfQQMygBegQIARA8..i&imgrefurl=https%3A%2F%2Fwww.facebook.com%2FIhyeonHighschool
                            Studentcouncil%2F&docid=-vgeVmnangISdM&w=576&h=388&q=%EC%9D%B4%ED%98%84%EA%B3%A0%20%EB%A1%9C%EA%B3%A0&
                            ved=2ahUKEwjO9a38yPj9AhXVRN4KHS88DfQQMygBegQIARA8" width="100" />
              </a>
            </div>
            <div class="col-2">
              <a class="btn btn-outline-primary" href="#" role="button">
                학교
              </a>
            </div>
            <div class="col-2">
              <a class="btn btn-outline-primary" href="#" role="button">
                학급
              </a>
            </div>
            <div class="col-2">
              <a class="btn btn-outline-primary" href="#" role="button">
                동아리
              </a>
            </div>
            <div class="col-2">
              <a class="btn btn-outline-primary" href="#" role="button">
                문의
              </a>
            </div>
            <div class="col-2">
              <a class="btn btn-outline-primary" href="#" role="button">
                로그인/회원가입
              </a>
            </div>
          </div>
        </div>
        <button class="next" @click="moveSectionDown">Next</button>
      </div>
      <div class="section">
        <h2>어제 만우절 기념 하늘에서 억까 실전 압축 모음 당한 사람</h2>
        <button class="prev" @click="moveSectionUp">Prev</button>
      </div>
    </full-page>
  </div>
</template>

<script>
export default {
}
</script>