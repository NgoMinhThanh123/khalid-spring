import { initializeApp } from "firebase/app";
import { getAuth } from 'firebase/auth';
import { getFirestore } from 'firebase/firestore';
import Apis, { endpoints } from "@/configs/Apis";

export async function fetchFirebaseConfig() {
  try {
      const response = await Apis.get(endpoints["get-firebase-config"]);
      const data = response.data;
      const firebaseConfig = {
          apiKey: data.apiKey,
          authDomain: data.authDomain,
          projectId: data.projectId,
          storageBucket: data.storageBucket,
          messagingSenderId: data.messagingSenderId,
          appId: data.appId,
        };
    
      return firebaseConfig;
  } catch (error) {
      console.error('Lỗi khi gọi API máy chủ:', error);
      throw error;
  }
}



// const firebaseConfig = await fetchFirebaseConfig();
// const app = initializeApp(firebaseConfig);
// export const auth = getAuth(app);
// export const db = getFirestore(app);