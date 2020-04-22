import React from "react";
import { useForm } from "react-hook-form";
import axios from "axios";
import "./CreateArtist.scss";
import { useStateValue } from "../state";

const CreateSongTitle = (props) => {
  const { register, errors, handleSubmit } = useForm();
  const [dispatch] = useStateValue();
  const onSubmit = (values) => {
    axios({
      url: "/update-song-title",
      method: "PUT",
      params: { song_id: values["song_id"], songName: values["SongTitle"] },
    })
      .then(function(response) {
        if (response.data) {
          alert("Changed song title successfully!");
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
          <h1>Change Song Title</h1>
        </div>

        <div className="input-container">
          <input
            name="SongTitle"
            ref={register({
              required: "A song title is required",
            })}
            placeholder="Song Title"
          />
        </div>
        <div className="input-container">
          <input
            name="song_id"
            ref={register({
              required: "A Song ID is required",
            })}
            placeholder="Song ID"
          />
        </div>

        <div className="submit-btn-group">
          <div>
            <button type="submit" className="btn btn-register">
              Submit
            </button>
          </div>
        </div>
        <div className="error-container">
          <p>{errors.username && errors.username.message}</p>
        </div>
      </div>
    </form>
  );
};

export default CreateSongTitle;
