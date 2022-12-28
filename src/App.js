import './App.css';
import React from 'react';
import {createStore} from 'redux';
import {Provider, useSelector, useDispatch} from 'react-redux';
import store from './store';
import {up} from './counterSlice';
/*
function reducer(state, action){
  const newState = {...state};
  if(action.type === 'up'){
    newState.value += action.step;
  }
  return newState;
}
const initialState = {value:0};
const store = createStore(reducer, initialState);
*/
function Counter(){
  const count = useSelector(state=>state.counter.value);
  const dispatch = useDispatch();
  return (
    <div>
      <button onClick={()=>{
        // dispatch({type:'counterSlice/up', step:2});
        dispatch(up(2)); // 액션크리에이터 : 액션을 직접 만들지 않고 payload라는 이름으로 값을 반환해줌
      }}>+</button> {count}
    </div>
  );
}
function App() {
  return (
    <Provider store={store}>
      <div>
        <Counter></Counter>
      </div>
    </Provider>
  );
}

export default App;
