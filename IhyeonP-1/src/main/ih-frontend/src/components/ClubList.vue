<template>
    <div class="container" style="height:fit-content;">
        <!-- <input type="file" id="upload-image" hidden />
    <label for="imageUploadLabel">
        <button type="button" class="btn btn-primary" v-on:click="chooseFiles()">button</button>
    </label> -->

        <!-- gallery -->
        <div v-if="!isUploading" class="container">
            <!-- main splide -->
            <div class="mainSplide">
                <splide id="splide" class="splide" :options="mainoptions" :extensions="extensions">
                    <splide-slide v-for="image of clubStore.getClub">
                        <div class="item" :style="{ 'background-image': `url(${image.image})` }">
                        </div>
                    </splide-slide>
                </splide>
            </div>
        </div>

        <!-- image upload -->
        <div class="container" style=" height: fit-content" v-if="isUploading">
            <div class="row justify-content-end">
                <div class="col-7">
                    <!-- imagePreview -->
                    <splide :options="preoptions">
                        <splide-slide v-for="image in previewImages">
                            <div class="imagePreviewWrapper" :style="{ 'background-image': `url(${image})` }"
                                @click="chooseFiles()">
                            </div>
                        </splide-slide>
                    </splide>
                </div>
                <div class="col">
                    <textarea @input="inputHandler" maxlength="100" class="form-control" rows="12" v-model="text"
                        style="resize: none;"></textarea>
                    <p id="lengthText">{{ 100 - text.length }}/100</p>
                    <div class="row justify-content-end" style="width:inherit">
                        <div class="col-3">
                            <button class="btn btn-outline-dark m-3" @click="cancelPost">Cancel</button>
                        </div>
                        <div class="col-2">
                            <button class="btn btn-outline-primary m-3" @click="uploadPost">Submit</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <input id="fileUpload" class="form-control" type="file" @input="pickFile" multiple hidden>
    </div>
    <button class="btn btn-primary" v-on:click="chooseFiles()">ㅇㅅㅇ</button>
</template>

<script>
import axios from 'axios';
import AuthService from '../services/auth.service';
import PhotoService from '../services/photo.service';
import { Form } from 'vee-validate';

import { onMounted, ref } from 'vue';
import { useStore } from 'vuex';

import { Splide, SplideSlide } from '@splidejs/vue-splide';
import { Grid } from '@splidejs/splide-extension-grid';
import "@splidejs/splide/dist/css/splide.min.css";
import vueFullpageUmd from 'vue-fullpage.js';

import { useClubStore } from '../store/club.js';

import { useToast } from "vue-toastification";

import VueDatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css';

export default {
    components: {
        Splide,
        SplideSlide,
    },
    name: "ClubList",
    emits: ["changeUploadImages", "imageData"],

    setup(props, context) {
        var previewImages = ref([]);
        var uploadImages = false;
        const isUploading = ref(false);
        var clubStore;

        const store = useStore();
        clubStore = useClubStore();

        const toast = useToast();

        const preoptions = {
            rewind: true,
            height: 400,
            gap: '1rem',
            perMove: 1,
            perPage: 1,
            pagination: true,
            postId: null,
        };

        const mainoptions = {
            // drag: "free",
            // autoWidth: true,
            perPage: 7,
            pagination: true,
            arrows: false,
            gap: 10,
            grid: {
                rows: 2,
                cols: 1,
                gap: {
                    row: "1rem",
                    col: "1.5rem",
                },
            },
        };

        const pickFile = (e) => {   //사진 파일 선택할 때
            if (e.target.files[0] && e.target.files.length < 7) {

                toggleUpload();
                previewImages.value = [];

                console.log("asfrg");

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
        const chooseFiles = () => {
            document.getElementById("fileUpload").click();
        }
        const uploadPost = async () => {    //글을 최종적으로 올릴 때
            const frm = new FormData();
            frm.append("userId", currentUser.id);
            frm.append("text", text.value)
            try {
                await PhotoService.uploadPhotoPost(frm).then((result) => {
                    postId = result.data;
                });
                text.value = "";
                await uploadImage();

            } catch (error) {
                if (error.response.status == 401) {
                    toast.clear()
                    toast.error(error.message, {
                        position: "bottom-right",
                        timeout: 30000,
                        toastClassName: "my-custom-toast-class",
                    })
                };
            }
        }
        const cancelPost = () => {
            toggleUpload();
        }

        const inputHandler = (e) => {
            const target = e.currentTarget;
            const max = e.currentTarget.getAttribute('maxlength');
            if (target.value.length > max) {
                target.value = target.value.slice(0, max);
            }
            // customString.value = target.value;
        };

        const toggleUpload = () => {
            isUploading = !isUploading;
            context.emit("changeUploadImages", isUploading);
        };
        return {
            pickFile,
            chooseFiles,
            uploadPost,
            cancelPost,
            inputHandler,
            toggleUpload,

            uploadImages,
            isUploading,
            previewImages,
            clubStore,

            preoptions: preoptions,
            mainoptions: mainoptions,
            extensions: {
                Grid,
            },
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