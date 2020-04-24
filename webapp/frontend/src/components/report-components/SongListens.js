import React from "react";
import { useForm } from "react-hook-form";
import { useHistory, Redirect } from "react-router-dom";
import axios from "axios";
import "../Login.scss";
import { useStateValue } from "../../state";

const SongListens = (props) => {
  const { handleSubmit, register, errors } = useForm();
  const [{ credentials }, dispatch] = useStateValue();

  let history = useHistory();

  const onSubmit = (values) => {
    axios
      .get("/get-song-listens", {
        params: { days: values["days"] },
      })
      .then(function(response) {
        if (response.data) {
          dispatch({
            type: "changeReport",
            changeReport: {
              artistSongs: [],
              popularSongs: [],
              artistsJoined: [],
              songListens: response.data,
              songsAdded: [],
              topSongs: [],
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
          <h1>Get Song Listens</h1>
        </div>
        <p>
          Returns the number of listens over the past given number of days,
          broken down by the day.
        </p>
        <p></p>

        <div className="input-container">
          <input
            name="days"
            ref={register({
              required: "Please specify the number of days.",
              max: 16,
            })}
            placeholder="Total Days"
          />
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

export default SongListens;
