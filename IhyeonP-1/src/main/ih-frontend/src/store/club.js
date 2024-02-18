// stores/counter.js
import { defineStore } from 'pinia'

export const useClubStore = defineStore('club', {
    state: () => ({club: []}),
    actions: {
        setClub(clubData){
            this.club = clubData;
        },
    },
    getters: {
        getClub(state){
            return state.club;
        },
    }
})

export default useClubStore