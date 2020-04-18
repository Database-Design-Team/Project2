import React, { useCallback, useState } from "react";
import axios from "axios";
import { useForm } from "react-hook-form";
import { useDropzone } from "react-dropzone";
import "./Upload.scss";

const Upload = (props) => {
  const { acceptedFiles, getRootProps, getInputProps } = useDropzone();

  const { handleSubmit, register, errors } = useForm();

  const onSubmit = (values) => {
    const file = acceptedFiles[0];
    const formData = new FormData();
    formData.append("file", file);
    formData.append("song_name", values.song_name);
    formData.append("musician", 1);
    const config = {
      headers: {
        "content-type": "multipart/form-data",
      },
    };
    axios
      .post("/upload-files", formData, config)
      .then(function(response) {
        if (response.data) {
          alert("Successfully uploaded files.");
        }
      })
      .catch(function(error) {
        console.log(error);
      });
    // TODO: get http response to inform user if file uploaded sucessfully
  };

  return (
    <div className="upload-container">
      <form onSubmit={handleSubmit(onSubmit)}>
        <div className="login-container">
          <div className="title-container">
            <h1>Enter song information.</h1>
          </div>

          <div className="song-input-container">
            <div className="input-container">
              <input
                name="song_name"
                ref={register({
                  required: "A song title is required",
                  max: 16,
                })}
                placeholder="Song Title"
              />
            </div>

            <div className="dropzone-container">
              <div {...getRootProps({ className: "dropzone" })}>
                <input {...getInputProps()} />
                <p>Drag 'n' drop some files here, or click to select files</p>
              </div>
            </div>

            <div className="submit-btn-group">
              <div>
                <button type="submit" className="btn btn-login">
                  Upload
                </button>
              </div>
            </div>
            <div className="error-container">
              <p>{errors.song_name && errors.song_name.message}</p>
            </div>
          </div>
        </div>
      </form>
    </div>
  );
};

export default Upload;
