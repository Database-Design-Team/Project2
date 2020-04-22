import React from "react";
import { useForm } from "react-hook-form";
import axios from "axios";
import "./CreateArtist.scss";
import { useStateValue } from "../state";

const AddUser = (props) => {
  const { register, errors, handleSubmit } = useForm();
  const [dispatch] = useStateValue();

  const onSubmit = (values) => {
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
      url: "/join-artist",
      method: "POST",
      params: {
        artist_id: values["artist_id"],
        username: values["username"],
      },
    })
      .then(function(response) {
        if (response.data) {
          alert("Added user successfully.");
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
          <h1>Add User</h1>
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
        <div className="input-container">
          <input
            name="artist_id"
            ref={register({
              required: "An artist ID is required",
            })}
            placeholder="Artist ID"
          />
        </div>

        <div className="submit-btn-group">
          <div>
            <button type="submit" className="btn btn-register">
              Add User
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

export default AddUser;
