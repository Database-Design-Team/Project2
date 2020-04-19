import React from "react";
import { useForm } from "react-hook-form";
import axios from "axios";
import "./CreateArtist.scss";

const CreateArtist = (props) => {
  const { register, errors, handleSubmit } = useForm();
  const onSubmit = (values) => {
    let artistName = values["username"];
    axios.interceptors.response.use(
      (response) => response,
      (error) => {
        const { status } = error.response;
        if (status === 409) {
          alert("The artist name has already been taken.");
        }
        window.location.reload(false);
        return Promise.reject(error);
      }
    );

    axios({
      url: "/artists",
      method: "POST",
      params: { artist_name: artistName },
    })
      .then(function(response) {
        if (response.data) {
          alert("artist created successfully");
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
          <h1>Create Artist</h1>
        </div>

        <div className="input-container">
          <input
            name="username"
            ref={register({
              required: "An artist name is required",
            })}
            placeholder="Artist Name"
          />
        </div>

        <div className="submit-btn-group">
          <div>
            <button type="submit" className="btn btn-register">
              Create Artist
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

export default CreateArtist;
