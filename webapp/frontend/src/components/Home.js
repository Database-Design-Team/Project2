import React from "react";
import "./Home.scss";
import { useStateValue } from "../state";

const Home = (props) => {
  const [{ credentials }, dispatch] = useStateValue();

  return (
    <div className="home-container">
      <h1>Welcome back {credentials.login_name}</h1>
    </div>
  );
};

export default Home;
