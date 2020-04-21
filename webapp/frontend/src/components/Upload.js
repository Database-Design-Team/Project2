import React, { useState, useEffect } from "react";
import axios from "axios";
import { useForm } from "react-hook-form";
import { useDropzone } from "react-dropzone";
import "./Upload.scss";
import { useStateValue } from "../state";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Modali, { useModali } from "modali";
import CreateRelease from "./CreateRelease";
import {
  faPlusCircle,
  faMinusCircle,
} from "@fortawesome/fontawesome-free-solid";

const Upload = (props) => {
  const { acceptedFiles, getRootProps, getInputProps } = useDropzone();
  const [createReleaseModal, togglecreateReleaseModal] = useModali({
    animated: true,
  });

  const [songGenre, setSongGenre] = useState("");
  const { handleSubmit, register, errors } = useForm();
  const [{ credentials }] = useStateValue();
  const [{ currentArtist, releaseList }, dispatch] = useStateValue();

  const [artistList, setArtistList] = useState({});

  function handleChange(e) {
    setSongGenre(e.target.value);
  }

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
        setArtistList(response.data);
        // dispatch({
        //   type: "changeArtistList",
        //   newArtists: { artists: response.data },
        // });
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
    formData.append("musician", currentArtist.artist_id);
    formData.append("genre", songGenre);
    const config = {
      headers: {
        "content-type": "multipart/form-data",
      },
    };
    axios
      .post("/upload-files", formData, config)
      .then(function(response) {
        if (response.data) {
          console.log(response.data);
          alert("Successfully uploaded files.");
        }
      })
      .catch(function(error) {
        console.log(error);
      });
  };

  // const changeCurrentRelease = (item, name) => {
  //   dispatch({
  //     type: "changeReleaseList",
  //     newReleases: {
  //       releases: releaseList.releases,
  //       release_id: item,
  //       release_name: name,
  //     },
  //   });
  // };

  const handleArtistChange = (item, i) => {
    console.log(item);
    dispatch({
      type: "changeCurrentArtist",
      changeArtist: { artist: item, artist_id: i },
    });
    axios({
      url: "/release-by-artist",
      method: "GET",
      responseType: "json",
      params: { artist_id: i },
    }).then((response) => {
      console.log(response.data);
      // dispatch({
      //   type: "changeReleaseList",
      //   newReleases: {
      //     releases: response.data,
      //     release_id: "",
      //     release_name: "No release selected.",
      //   },
      // });
    });
  };

  // const handleDeleteRelease = (item) => {
  //   axios({
  //     url: "/remove-release",
  //     method: "DELETE",
  //     params: {
  //       release_id: item,
  //     },
  //   }).then((response) => {
  //     axios({
  //       url: "/release-by-artist",
  //       method: "GET",
  //       responseType: "json",
  //       params: { artist_id: currentArtist.artist_id },
  //     }).then((response) => {
  //       console.log(response.data);
  //       dispatch({
  //         type: "changeReleaseList",
  //         newReleases: {
  //           releases: response.data,
  //           release_id: "",
  //           release_name: "No release selected.",
  //         },
  //       });
  //     });
  //   });
  // };

  const handleDeleteMember = (item) => {
    dispatch({
      type: "changeCurrentArtist",
      changeArtist: { artist: "No artist selected.", artist_id: "" },
    });
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
          setArtistList(response.data);
          // dispatch({
          //   type: "changeArtistList",
          //   newArtists: { artists: response.data },
          // });
          // dispatch({
          //   type: "changeReleaseList",
          //   newReleases: { releases: {} },
          // });
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
            {Object.keys(artistList)
              .reverse()
              .map((item, i) => (
                <li
                  className="current-member-artists-element-container"
                  key={i}
                >
                  <p>{Object.keys(artistList[item])}</p>
                  <FontAwesomeIcon
                    className="fa-icon add"
                    icon={faPlusCircle}
                    onClick={() =>
                      handleArtistChange(Object.keys(artistList[item]), item)
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
        {/* <button className="btn btn--1 btnCA" onClick={togglecreateReleaseModal}>
          Create a New Release
        </button>
        <Modali.Modal {...createReleaseModal}>
          <CreateRelease />
        </Modali.Modal> */}
      </div>
      <form onSubmit={handleSubmit(onSubmit)}>
        <div className="">
          <div className="">
            <p>{`Uploading as: ${currentArtist.artist}`}</p>
            {/* <p>{`Uploading to release: ${releaseList.release_name}`}</p> */}
          </div>
          {/* <div className="main-release-container">
            <ul className="release-container">
              {Object.keys(releaseList.releases)
                .reverse()
                .map((item, i) => (
                  <li
                    className="current-member-artists-element-container"
                    key={i}
                  >
                    <p>{Object.keys(releaseList.releases[item])}</p>
                    <FontAwesomeIcon
                      className="fa-icon add"
                      icon={faPlusCircle}
                      onClick={() =>
                        changeCurrentRelease(
                          item,
                          Object.keys(releaseList.releases[item])
                        )
                      }
                    />
                    <FontAwesomeIcon
                      className="fa-icon remove"
                      icon={faMinusCircle}
                      onClick={() => handleDeleteRelease(item)}
                    />
                  </li>
                ))}
            </ul>
          </div> */}
          <div className="song-input-container">
            <p>Enter song information.</p>
            <div className="input-container">
              <input
                name="song_name"
                ref={register({
                  required: "A song title is required",
                  max: 16,
                })}
                placeholder="Song Title"
              />
              <select onChange={handleChange} value={songGenre}>
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
