import React, { useState, useEffect } from "react";
import "./Home.scss";
import axios from "axios";
import { useStateValue } from "../state";

const Home = (props) => {
  const [{ credentials }, dispatch] = useStateValue();
  const [userInfo, setUserInfo] = useState({});

  useEffect(() => {
    const abortController = new AbortController();
    // const signal = abortController.signal;

    axios({
      url: "/user-account-info",
      method: "GET",
      responseType: "json",
      params: {
        username: credentials.username,
      },
    }).then((response) => {
      let json = JSON.parse(
        JSON.stringify(response.data, [
          "email",
          "student_id",
          "username",
          "password",
          "date_joined",
        ])
      );
      setUserInfo(json);
    });
    return function cleanup() {
      abortController.abort();
    };
  }, []);

  return (
    <div className="home-tab-container">
      <div className="home-header">
        <p>Welcome back {credentials.username}!</p>
      </div>
      <div className="asdf-content-container">
        <div className="profile-info-container">
          <p>Profile Information</p>
          <div className="profile-info-list">
            {Object.keys(userInfo).map((item, i) => (
              <div className="input-container">
                <label for="credentials">{item}</label>
                <input id="credentials" placeholder={userInfo[item]}></input>
              </div>
            ))}
          </div>
        </div>
        <div className="stats-container">
          <p>Top 10 Songs</p>
        </div>
      </div>
    </div>
  );
};

export default Home;
