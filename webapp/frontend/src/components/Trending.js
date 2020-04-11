import React from "react";
import "./Trending.scss";
import rodeo from "../resources/images/rodeo.jpg";
import dielit from "../resources/images/dielit.jpg";
import beatles from "../resources/images/beatles.jpg";
import hendrix from "../resources/images/hendrix.jpg";

const Trending = () => {
    return (
        <div className="trending-body-container">
            <div className="trending-title-container">
                <h2>Trending Songs</h2>
            </div>
            <div className="song-container">
                <div className="first">
                    <img className="first-song" src={rodeo} alt="rodeo"></img>
                </div>
                <div className="second">
                    <img
                        className="second-song"
                        src={dielit}
                        alt="dielit"
                    ></img>
                </div>
                <div className="third">
                    <img className="third-song" src={beatles} alt="beatles" />
                </div>
                <div className="fourth">
                    <img className="fourth-song" src={hendrix} alt="hendrix" />
                </div>
            </div>
        </div>
    );
};

export default Trending;
