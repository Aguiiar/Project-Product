import React, { useState, useEffect } from "react";
import axios from "axios";
import Register from "./components/Register";
import Buy from "./components/Buy";
import { Route, Routes } from "react-router-dom";
import Home from "./components/Home";
import Login from "./components/Login"

function App() {
  const [products, setProducts] = useState([]);

  const isAuth = localStorage.getItem("auth");


  useEffect(() => {
    axios
      .get("http://localhost:8080/products")
      .then((res) => setProducts(res.data))
      .catch((err) => console.error(err));
  }, []);

  return (
    <Routes>
      <Route path="/Home" element={<Home/>}/>
   
      <Route path="/buy" element={<Buy/>}/>
    
      <Route path="/register" element={isAuth ?  <Register/> : <Login/>} />
  
      <Route path="/" element={<Login/>}/>

      </Routes>
  );
}

export default App;
