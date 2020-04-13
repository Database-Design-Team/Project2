import React from "react";
import { useForm } from "react-hook-form";
import { useHistory } from "react-router-dom";
import axios from "axios";
import "./Login.scss";

const Login = props => {
  const { handleSubmit, register, errors } = useForm();
  let history = useHistory();

  const onSubmit = values => {
    console.log(values);

    axios
      .post("/user-credentials-login", {
        login_name: values["username"],
        password: values["password"]
      })
      .then(function(response) {
        if (response.data) {
          history.push("/dashboard");
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
          <h1>Sign in</h1>
        </div>

        <div className="input-container">
          <input
            name="username"
            ref={register({
              required: "A username is required",
              max: 16
            })}
            placeholder="username"
          />
        </div>
        <div className="input-container">
          <input
            type="password"
            name="password"
            ref={register({
              required: "A password is required",
              min: 8,
              max: 32
            })}
            placeholder="••••••••"
          />
        </div>
        <div className="submit-btn-group">
          <div>
            <button type="submit" className="btn btn-login">
              Sign in
            </button>
          </div>
        </div>
        <div className="error-container">
          <p>{errors.username && errors.username.message}</p>
          <p>{errors.password && errors.password.message}</p>
        </div>
      </div>
    </form>
  );
};

export default Login;
