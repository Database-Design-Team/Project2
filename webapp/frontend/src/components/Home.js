import React, { useState, useEffect } from "react";
import "./Home.scss";
import axios from "axios";
import { useStateValue } from "../state";
import Modali, { useModali } from "modali";
import ChangePassword from "./ChangePassword";

const Home = (props) => {
  const [{ credentials }] = useStateValue();
  const [userInfo, setUserInfo] = useState({});
  const [top10Info, setTop10Info] = useState([]);
  const [changePassword, toggleChangePassword] = useModali({
    animated: true,
  });

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
      axios
        .get("/get-popular-songs", {
          params: { totalSongs: 10 },
        })
        .then(function(response) {
          if (response.data) {
            setTop10Info(response.data);
          }
        })
        .catch(function(error) {
          console.log(error);
        });
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
              <div className="credentials">
                <div>
                  <p>{`${item}:`}</p>
                  <p>{userInfo[item]}</p>
                </div>
              </div>
            ))}
          </div>
          <button className="btn btn--1 btnCA" onClick={toggleChangePassword}>
            Change Password
          </button>
          <Modali.Modal {...changePassword}>
            <ChangePassword />
          </Modali.Modal>
        </div>
        <div className="stats-container">
          <p>Top 10 Songs</p>
          <ul>
            {top10Info.map((item) => (
              <li>
                <div>{`Artist Name: ${item.ArtistName}`}</div>
                <div>{`Song Name: ${item.SongName}`}</div>
                <div>{`Rating: ${item.Rating}`}</div>
              </li>
            ))}
          </ul>
        </div>
      </div>
    </div>
  );
};

export default Home;
