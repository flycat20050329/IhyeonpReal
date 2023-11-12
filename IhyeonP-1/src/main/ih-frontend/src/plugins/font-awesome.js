import { library } from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import {
  faHome,
  faUser,
  faUserPlus,
  faSignInAlt,
  faSignOutAlt,
  faPlus,
} from "@fortawesome/free-solid-svg-icons";

import {
  faHeart
} from "@fortawesome/free-regular-svg-icons";

library.add(faHome, faUser, faUserPlus, faSignInAlt, faSignOutAlt, faPlus, faHeart);

export { FontAwesomeIcon };
