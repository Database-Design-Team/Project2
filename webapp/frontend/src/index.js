import React from "react";
import ReactDOM from "react-dom";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import "./index.scss";
import Landing from "./components/Landing";
import Dashboard from "./components/Dashboard";
import { StateProvider } from "./state";

function App() {
  const initialState = {
    currentSong: { url: "" },
    credentials: { username: "", password: "" },
    artistList: { artists: {} },
    currentArtist: { artist: "No artist selected.", artist_id: "" },
    releaseList: {
      releases: {},
      release_id: "",
      release_name: "No release selected.",
    },
  };

  const reducer = (state, action) => {
    switch (action.type) {
      case "changeSong":
        return {
          ...state,
          currentSong: action.newSong,
        };
      case "changeCredentials":
        return {
          ...state,
          credentials: action.postCredentials,
        };
      case "changeArtistList":
        return {
          ...state,
          artistList: action.newArtists,
        };
      case "changeCurrentArtist":
        return {
          ...state,
          currentArtist: action.changeArtist,
        };
      case "changeReleaseList":
        return {
          ...state,
          releaseList: action.newReleases,
        };
      default:
        return state;
    }
  };

  return (
    <StateProvider initialState={initialState} reducer={reducer}>
      <Switch>
        <Route exact path="/" component={Landing} />
        <Route path="/dashboard" component={Dashboard} />
      </Switch>
    </StateProvider>
  );
}

ReactDOM.render(
  <Router>
    <App />
  </Router>,
  document.getElementById("root")
);
