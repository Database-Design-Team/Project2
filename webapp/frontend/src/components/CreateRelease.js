import React from "react";
import { useForm } from "react-hook-form";
import axios from "axios";
import "./CreateArtist.scss";
import { useStateValue } from "../state";

const CreateArtist = (props) => {
  const { register, errors, handleSubmit } = useForm();
  const [{ artistList, currentArtist }, dispatch] = useStateValue();

  const onSubmit = (values) => {
    let releaseName = values["username"];
    let genre = values["genre"];
    console.log(releaseName);
    console.log(genre);
    axios({
      url: "/add-release",
      method: "POST",
      params: { title: releaseName, artist: currentArtist.artist_id },
    })
      .then(function(response) {
        if (response.data) {
          alert("Release created successfully");
          const abortController = new AbortController();
          // const signal = abortController.signal;

          return function cleanup() {
            abortController.abort();
          };
        }
      }, [])
      .catch(function(error) {
        console.log(error);
      });
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <div className="login-container">
        <div className="title-container">
          <h1>Create a New Release</h1>
        </div>
        <div className="input-container">
          <input
            name="username"
            ref={register({
              required: "A release title is required",
            })}
            placeholder="Release Title"
          />
        </div>
        <div className="input-container">
          <input
            name="genre"
            ref={register({
              required: "A genre is required",
            })}
            placeholder="Genre"
          />
        </div>

        <div className="submit-btn-group">
          <div>
            <button type="submit" className="btn btn-register">
              Create Release
            </button>
          </div>
        </div>
        <div className="error-container">
          <p>{errors.username && errors.username.message}</p>
          <p>{errors.genre && errors.genre.message}</p>
        </div>
      </div>
    </form>
  );
};

export default CreateArtist;
