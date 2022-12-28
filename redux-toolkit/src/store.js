import {configureStore} from '@reduxjs/toolkit';
import counterSlice from './counterSlice';

// configureStore : 작은 슬라이스들을 모아서 스토어로 만들어주는 함수
const store = configureStore({
  reducer:{
    counter:counterSlice.reducer
  }
});
export default store;