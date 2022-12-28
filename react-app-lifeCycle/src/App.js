import React, {useState, useEffect} from 'react';
import './App.css';

function App() {
  const [funcShow, setFuncShow] = useState(true);
  const [classShow, setClassShow] = useState(true);
  return (
    <div className="container">
      <h1>Hello World</h1>
      <input type="button" value="remove func" onClick={function(){
        setFuncShow(false);
      }}/>
      <input type="button" value="remove class" onClick={function(){
        setClassShow(false);
      }}/>
      {funcShow ? <FuncComp initNumber={2}></FuncComp> : null}
      {classShow ? <ClassComp initNumber={2}></ClassComp> : null}
    </div>
  );
}

let funcStyle = 'color:blue';
let funcId = 0;
function FuncComp(props){
  const [number, setNumber] = useState(props.initNumber);
  const [dateTime, setDateTime] = useState((new Date()).toString());

  useEffect(function(){
    console.log('%cfunc => useEffect (componentWillMount)' + (++funcId), funcStyle);
    document.title = number;
    return function(){
      console.log('%cfunc => useEffect return (componentWillUnMount)' + (++funcId), funcStyle);
    }
  }, []);

  // side effect
  useEffect(function(){
    console.log('%cfunc => useEffect number (componentDidMount & componentDidUpdate)' + (++funcId), funcStyle);
    document.title = number;
    return function(){
      console.log('%cfunc => useEffect number return (componentDidMount & componentDidUpdate)' + (++funcId), funcStyle);
    }
  }, [number]);

  useEffect(function(){
    console.log('%cfunc => useEffect date (componentDidMount & componentDidUpdate)' + (++funcId), funcStyle);
    document.title = dateTime;
    return function(){
      console.log('%cfunc => useEffect date return (componentDidMount & componentDidUpdate)' + (++funcId), funcStyle);
    }
  }, [dateTime]);

  console.log('%cfunc => render' + (++funcId), funcStyle);
  return (
    <div className="container">
      <h2>function style component</h2>
      <p>Number : {number}</p>
      <p>DateTime : {dateTime}</p>
      <input type="button" value="random" onClick={function(){
        setNumber(Math.random());
      }}/>
      <input type="button" value="date/time" onClick={function(){
        setDateTime((new Date()).toString());
      }}/>
    </div>
  )
}

let classStyle = 'color:red';
class ClassComp extends React.Component{
  state = {
    number:this.props.initNumber,
    dateTime:(new Date()).toString()
  }
  componentWillMount(){
    console.log('%cclass => componentWillMount', classStyle);
  }
  componentDidMount(){
    console.log('%cclass => componentDidMount', classStyle);
  }
  shouldComponentUpdate(){
    console.log('%cclass => shouldComponentUpdate', classStyle);
    return true;
  }
  componentWillUpdate(){
    console.log('%cclass => componentWillUpdate', classStyle);
  }
  componentDidUpdate(){
    console.log('%cclass => componentDidUpdate', classStyle);
  }
  componentWillUnmount(){
    console.log('%cclass => componentWillUnmount', classStyle);
  }
  render(){
    console.log('%cclass => render', classStyle);
    return (
      <div className="container">
        <h2>class style component</h2>
        <p>Number : {this.state.number}</p>
        <p>DateTime : {this.state.dateTime}</p>
        <input type="button" value="random" onClick={
          function(){
            this.setState({number:Math.random()});
          }.bind(this)
        }/>
        <input type="button" value="date/time" onClick={
          function(){
            this.setState({dateTime:(new Date()).toString()});
          }.bind(this)
        }/>
      </div>
    )
  }
}
export default App;
