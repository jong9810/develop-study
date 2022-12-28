import {createSlice} from '@reduxjs/toolkit';

// redux-toolkit 설치 : npm install @reduxjs/toolkit
// 슬라이스는 스토어를 기능별로 작게 쪼갠 것이다.
const counterSlice = createSlice({
    name:'counterSlice',
    initialState:{value:0},
    reducers:{
      up:(state, action)=>{
        console.log(action);
        state.value += action.payload;
      }
    }
  });
  export default counterSlice;
  export const {up} = counterSlice.actions;