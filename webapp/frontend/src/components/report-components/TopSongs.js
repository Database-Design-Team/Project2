import React, { useState } from "react";
import { useForm } from "react-hook-form";
import { useHistory, Redirect } from "react-router-dom";
import axios from "axios";
import "../Login.scss";
import { useStateValue } from "../../state";

const TopSongs = (props) => {
  const { handleSubmit, register, errors } = useForm();
  const [songGenre, setSongGenre] = useState("");
  const [{ credentials }, dispatch] = useStateValue();
  function handleChange(e) {
    setSongGenre(e.target.value);
  }

  let history = useHistory();

  const onSubmit = (values) => {
    axios
      .get("/get-top-songs", {
        params: {
          genre: songGenre,
          days: values["days"],
          songs: values["songs"],
        },
      })
      .then(function(response) {
        if (response.data) {
          dispatch({
            type: "changeReport",
            changeReport: {
              artistSongs: [],
              popularSongs: [],
              artistsJoined: [],
              songListens: [],
              songsAdded: [],
              topSongs: response.data,
            },
          });
        }
      })
      .catch(function(error) {
        console.log(error);
      });
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <div className="login-container">
        <div className="title-container">
          <h1>Get Top Songs</h1>
        </div>

        <div className="input-container">
          <input
            name="days"
            ref={register({
              required: "Please specify the number of days.",
              max: 16,
            })}
            placeholder="Total Days"
          />
          <input
            name="songs"
            ref={register({
              required: "Please specify the number of songs.",
              max: 16,
            })}
            placeholder="Total Songs"
          />
          <div className="dropdown">
            <select
              id="genre"
              className="dropdown-select"
              onChange={handleChange}
              value={songGenre}
            >
              <option value="">--Choose a genre--</option>
              <option value="1">Classical</option>
              <option value="2">HipHop</option>
              <option value="3">Rap</option>
              <option value="4">Country</option>
              <option value="5">Folk</option>
              <option value="6">Rock</option>
              <option value="7">Pop</option>
              <option value="8">Jazz</option>
              <option value="9">Metal</option>
              <option value="10">Punk</option>
              <option value="11">Instrumental</option>
              <option value="12">Acapella</option>
              <option value="13">Dubstep</option>
              <option value="14">Blues</option>
              <option value="15">Ska</option>
              <option value="16">New Age</option>
            </select>
          </div>
        </div>
        <div className="submit-btn-group">
          <div>
            <button type="submit" className="btn btn-login">
              Submit
            </button>
          </div>
        </div>
        <div className="error-container">
          <p>{errors.totalSongs && errors.totalSongs.message}</p>
        </div>
      </div>
    </form>
  );
};

export default TopSongs;
