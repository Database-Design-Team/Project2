import React, { useState, useEffect } from "react";
import axios from "axios";
import { useForm } from "react-hook-form";
import { useDropzone } from "react-dropzone";
import "./Upload.scss";
import { useStateValue } from "../state";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faPlusCircle,
  faMinusCircle,
} from "@fortawesome/fontawesome-free-solid";

const Upload = (props) => {
  const { acceptedFiles, getRootProps, getInputProps } = useDropzone();

  const { handleSubmit, register, errors } = useForm();
  const [{ credentials }] = useStateValue();
  const [{ artistList, currentArtist }, dispatch] = useStateValue();

  useEffect(() => {
    const abortController = new AbortController();
    // const signal = abortController.signal;

    axios({
      url: "/getAllArtistsByBandMember",
      method: "GET",
      responseType: "json",
      params: { band_member: credentials.username },
    })
      .then((response) => {
        dispatch({
          type: "changeArtistList",
          newArtists: { artists: response.data },
        });
      })
      .catch(function(error) {
        console.error(error);
      });

    return function cleanup() {
      abortController.abort();
    };
  }, []);

  const onSubmit = (values) => {
    const file = acceptedFiles[0];
    const formData = new FormData();
    formData.append("file", file);
    formData.append("song_name", values.song_name);
    formData.append("musician", 1); //change this to the artist_id
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
  };

  const handleArtistChange = (item) => {
    dispatch({
      type: "changeCurrentArtist",
      changeArtist: { artist: `Uploading as ${item}` },
    });
    //TODO: Render list of releases under current artist.
  };

  const handleDeleteMember = (item) => {
    axios({
      url: "/remove-artist-member",
      method: "DELETE",
      params: {
        artist_id: item,
        username: credentials.username,
      },
    })
      .then((response) => {
        axios({
          url: "/getAllArtistsByBandMember",
          method: "GET",
          responseType: "json",
          params: { band_member: credentials.username },
        }).then((response) => {
          dispatch({
            type: "changeArtistList",
            newArtists: { artists: response.data },
          });
        });
      })
      .catch(function(error) {
        console.error(error);
      });
  };

  return (
    <div className="upload-container">
      <div className="band-container">
        <div className="band-header-container">
          <p>Choose an artist to upload under.</p>
        </div>
        <div className="member-artists-container">
          <ul className="current-member-artists-container">
            {Object.keys(artistList.artists)
              .reverse()
              .map((item, i) => (
                <li
                  className="current-member-artists-element-container"
                  key={i}
                >
                  <p>{Object.keys(artistList.artists[item])}</p>
                  <FontAwesomeIcon
                    className="fa-icon add"
                    icon={faPlusCircle}
                    onClick={() =>
                      handleArtistChange(Object.keys(artistList.artists[item]))
                    }
                  />
                  <FontAwesomeIcon
                    className="fa-icon remove"
                    icon={faMinusCircle}
                    onClick={() => handleDeleteMember(item)}
                  />
                </li>
              ))}
          </ul>
        </div>
      </div>
      <form onSubmit={handleSubmit(onSubmit)}>
        <div className="">
          <div className="">
            <p>{currentArtist.artist}</p>
            <p>Enter song information.</p>
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
                <p>Drag and drop your song here, or click to select files</p>
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
