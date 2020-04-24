import React from "react";
import { useForm } from "react-hook-form";
import { useHistory, Redirect } from "react-router-dom";
import axios from "axios";
import "../Login.scss";
import { useStateValue } from "../../state";

const ArtistSongs = (props) => {
  const { handleSubmit, register, errors } = useForm();
  const [{ credentials }, dispatch] = useStateValue();

  let history = useHistory();

  const onSubmit = (values) => {
    axios
      .get("/get-artist-songs", {
        params: { days: values["days"] },
      })
      .then(function(response) {
        if (response.data) {
          dispatch({
            type: "changeReport",
            changeReport: {
              artistSongs: response.data,
              popularSongs: [],
              artistsJoined: [],
              songListens: [],
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
          <h1>Get Most Active Artists</h1>
        </div>
        <p>
          Returns a table of the artists and the amount of songs they uploaded
          in the past number of days, from most active to least active.
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

export default ArtistSongs;
