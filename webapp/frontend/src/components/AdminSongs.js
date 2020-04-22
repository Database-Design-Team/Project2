import React, { useState, useEffect } from "react";
import { useForm } from "react-hook-form";
import "./AdminSongs.scss";
import axios from "axios";
import { useStateValue } from "../state";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMinusCircle } from "@fortawesome/fontawesome-free-solid";

const AdminSongs = (props) => {
  const [{ credentials }, dispatch] = useStateValue();
  const { handleSubmit, register, errors } = useForm();
  const [list, setList] = useState({});
  useEffect(() => {
    const abortController = new AbortController();
    // const signal = abortController.signal;

    axios({
      url: "/getAllSongs",
      method: "GET",
      responseType: "json",
    }).then((response) => {
      setList(response.data);
    });
    return function cleanup() {
      abortController.abort();
    };
  }, []);

  const handleDeleteSong = (song_id) => {
    console.log(`Deleting song with id: ${song_id}`);
  };

  const onSubmit = (values) => {
    console.log(values["songName"]);
  };

  return (
    <div className="feed-container">
      <div className="feed-title-container">
        <p>Edit songs.</p>
      </div>
      <div className="admin-feed-headers-container">
        <p>Title</p>
        <p>Artist</p>
        <p>Delete Song</p>
      </div>
      <ul className="feed-grid-container">
        {Object.keys(list)
          .reverse()
          .map((item, i) => (
            <form onSubmit={handleSubmit(onSubmit)}>
              <li className="admin-feed-element-container" key={i}>
                <div className="input-container">
                  <input
                    name="songName"
                    ref={register({
                      required: "A change is required",
                    })}
                    placeholder={list[item].split("|")[1]}
                  />
                </div>
                <input type="submit" style={{ display: "none" }} />
                {/* <p>{list[item].split("|")[1]}</p> */}
                <p>{list[item].split("|")[0]}</p>
                <FontAwesomeIcon
                  className="fa-icon add"
                  icon={faMinusCircle}
                  onClick={() => handleDeleteSong(item)}
                />
              </li>
            </form>
          ))}
      </ul>
    </div>
  );
};

export default AdminSongs;
