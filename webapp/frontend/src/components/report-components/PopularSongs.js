import React from "react";
import { useForm } from "react-hook-form";
import { useHistory, Redirect } from "react-router-dom";
import axios from "axios";
import "../Login.scss";
import { useStateValue } from "../../state";

const PopularSongs = (props) => {
  const { handleSubmit, register, errors } = useForm();
  const [{ credentials }, dispatch] = useStateValue();

  let history = useHistory();

  const onSubmit = (values) => {
    axios
      .get("/get-popular-songs", {
        params: { totalSongs: values["totalSongs"] },
      })
      .then(function(response) {
        if (response.data) {
          dispatch({
            type: "changeReport",
            changeReport: {
              artistSongs: [],
              popularSongs: response.data,
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
          <h1>Get Most Popular Songs</h1>
        </div>
        <p>
          Returns the top x most highly rated songs, the artist who released the
          song, and the rating.
        </p>
        <p></p>
        <div className="input-container">
          <input
            name="totalSongs"
            ref={register({
              required: "Please specify the number of songs.",
              max: 16,
            })}
            placeholder="Total Songs"
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

export default PopularSongs;
