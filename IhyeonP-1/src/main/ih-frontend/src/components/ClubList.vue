<template>
    <div class="container" style="height:fit-content;">
        <!-- <input type="file" id="upload-image" hidden />
    <label for="imageUploadLabel">
        <button type="button" class="btn btn-primary" v-on:click="chooseFiles()">button</button>
    </label> -->

        <!-- image upload -->
        <div class="container" style=" height: fit-content" v-if="isUploading">
            <div class="row justify-content-end">
                <div class="col-7">
                    <!-- imagePreview -->
                    <splide :options="preoptions">
                        <splide-slide v-for="image in previewImages">
                            <div class="imagePreviewWrapper" :style="{ 'background-image': `url(${image})` }"
                                @click="chooseClubFiles()">
                            </div>
                        </splide-slide>
                    </splide>
                </div>
                <div class="col">
                    <textarea @input="clubInputHandler" maxlength="1000" class="form-control" rows="12" v-model="clubText"
                        style="resize: none;"></textarea>
                    <p id="lengthText">{{ 1000 - clubText.length }}/1000</p>
                    <div class="row justify-content-end" style="width:inherit">
                    </div>
                    <div class="col-3">
                        <button class="btn btn-outline-dark m-3" @click="cancelClubPost">Cancel</button>
                    </div>
                    <div class="col-2">
                        <button class="btn btn-outline-primary m-3" @click="uploadClubPost">Upload</button>
                    </div>
                </div>
            </div>
        </div>
        <input id="clubFileUpload" class="form-control" type="file" @input="clubPickFile" multiple hidden>
    </div>
</template>

<script>
import { onMounted, ref } from 'vue';
import { useStore } from 'vuex';

import { Splide, SplideSlide } from '@splidejs/vue-splide';
import "@splidejs/splide/dist/css/splide.min.css";

import { useClubStore } from '../store/club.js';
import ClubService from "../services/club.service";

import moment from "moment";
import { useToast } from "vue-toastification";
import '@vuepic/vue-datepicker/dist/main.css';

export default {
    components: {
        Splide,
        SplideSlide,
    },
    name: "ClubList",
    emits: ["toggleUpload", "imageData"],

    setup(props, context) {
        var images = [];
        var previewImages = ref([]);
        var isUploading = ref(false);
        var clubStore;
        var clubText = ref("");
        var postId;

        const store = useStore();
        clubStore = useClubStore();
        const currentUser = store.state.auth.user;

        const toast = useToast();

        const clubPickFile = (e) => {   //사진 파일 선택할 때
            if (e.target.files[0] && e.target.files.length < 7) {

                toggleUpload(true);
                context.emit("toggleUpload", true);
                previewImages.value = [];

                var files = e.target.files || e.dataTransfer.files;
                images = files;

                if (files && files[0]) {
                    for (var i = 0; i < files.length; i++) {
                        let reader = new FileReader;
                        reader.onload = e => {
                            previewImages.value.push(e.target.result);
                        }
                        reader.readAsDataURL(files[i])
                    }
                }
            }
        }

        const toggleUpload = (data) => {
            isUploading.value = data;
        };

        const chooseClubFiles = () => {
            document.getElementById("clubFileUpload").click();
        };

        const clubInputHandler = (e) => {
            const target = e.currentTarget;
            const max = e.currentTarget.getAttribute('maxlength');
            if (target.value.length > max) {
                target.value = target.value.slice(0, max);
            }
            // customString.value = target.value;
        };

        const cancelClubPost = () => {
            isUploading.value = false;
            context.emit("toggleUpload", false);
        }

        const uploadClubPost = () => {
            var clubPostData = new FormData();
            clubPostData.append("id", currentUser.id);
            clubPostData.append("text", clubText.value);

            ClubService.uploadClubPost(clubPostData).then((result) => {
                postId = result.data;
            });
        }

        return {
            clubPickFile,
            toggleUpload,
            chooseClubFiles,
            clubInputHandler,
            cancelClubPost,
            uploadClubPost,

            isUploading,
            previewImages,
            clubStore,
            clubText,
            currentUser,
            postId,

        }
    },
    mounted() {
        console.log("mounted");
    },
}
</script>

<style scoped lang="scss">
@import url("../../node_modules/@egjs/vue3-flicking/dist/flicking-inline.css");

.splide {
    background-color: #d7d7d72b;
}

.item {
    height: 200px;
    max-width: 200px;
    object-fit: cover;
    display: block;
    cursor: pointer;
    margin: 5px auto 0 auto;
    background-size: cover;
    // background-size: contain;
    // background-repeat: no-repeat;
    background-position: center center;
    background-color: white;
    border: 1px solid rgba(165, 165, 165, 0.539);
    // transition: width 1s ease;
    padding: 3px 3px 3px 3px;
}

.imagePreviewWrapper {
    height: 400px;
    max-width: 700px;
    object-fit: cover;
    display: block;
    cursor: pointer;
    margin: 0 auto 30px;
    background-size: contain;
    background-position: center center;
    // border: 1px solid gray;
}

.topBar {
    border-bottom: solid;
    padding-bottom: 5px;
}

.topBar .searchBar {
    display: flex;
    justify-content: flex-end;
    padding-right: 15px;
}

.form .fa-search {
    position: absolute;
    top: 12px;
    left: 15px;
    color: #000000;

}

.form {
    position: relative;
    width: fit-content;
}

.form-input {
    height: 38px;
    width: 350px;
    text-indent: 33px;
    border-radius: 40px;
}

.form-input:focus {
    box-shadow: none;
    border: none;
}

#lengthText {
    text-align: right;
    margin-top: -27px;
    margin-right: 10px;
    padding-right: 1%;
    color: gray;
}
</style>