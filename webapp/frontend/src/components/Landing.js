import React, { useState, useEffect } from "react";
import Modali, { useModali } from "modali";
import "./Landing.scss";
import Login from "./Login.js";
import Register from "./Register.js";
import Trending from "./Trending.js";

const Landing = (props) => {
  const [loginModal, toggleLoginModal] = useModali({
    animated: true,
  });
  const [registerModal, toggleRegisterModal] = useModali({
    animated: true,
  });

  return (
    <div className="landing-container">
      <div className="header-container">
        <div className="home-container">Coog Music</div>
        <div className="btn-group">
          <div>
            <button className="btn btn--1" onClick={toggleLoginModal}>
              Sign in
            </button>
          </div>
          <div>
            <button className="btn btn--2" onClick={toggleRegisterModal}>
              Sign up
            </button>
          </div>
          <Modali.Modal {...loginModal}>
            <Login />
          </Modali.Modal>
          <Modali.Modal {...registerModal}>
            <Register />
          </Modali.Modal>
        </div>
      </div>
      <div className="content-container">
        <div className="description">
          <div className="desc-text">
            <p>A music streaming platform made for the University of Houston</p>
            <p>
              Upload and stream music for other students and alumni to discover.
            </p>
          </div>
        </div>
        <div className="trending-container">
          <Trending />
        </div>
        <div className="info-container">
          <p>
            With Coog Music, artists and creators within the University of
            Houston can easily share and connect with each other through their
            music.
          </p>
        </div>
      </div>
      <div className="footer-container">
        <div className="info">
          <h5> © 2020, Coog Music, Inc.</h5>
        </div>
      </div>
    </div>
  );
};

export default Landing;
