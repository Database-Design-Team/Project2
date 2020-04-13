import React, { useState } from "react";
import { useForm } from "react-hook-form";
import { useHistory } from "react-router-dom";
import axios from "axios";
import "./Register.scss";

const Register = props => {
  // const { handleSubmit, register, errors } = useForm();
  const { register, errors, getValues, handleSubmit } = useForm();
  let history = useHistory();
  const onSubmit = values => {
    delete values["passwordConfirmation"];
    console.log(values);

    axios
      .post("/user-credentials-register", {
        login_name: values["username"],
        email: values["email"],
        password: values["password"]
      })
      .then(function(response) {
        console.log(response);
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
          <h1>Sign up</h1>
        </div>

        <div className="input-container">
          <input
            name="email"
            ref={register({
              required: "Required",
              pattern: {
                value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i,
                message: "Please enter a valid email address."
              }
            })}
            placeholder="student@uh.edu"
          />
        </div>
        <div className="input-container">
          <input
            name="username"
            ref={register({
              required: "Required",
              max: 20
            })}
            placeholder="username"
          />
        </div>

        <div className="input-container">
          <input
            name="password"
            type="password"
            placeholder="••••••••"
            ref={register({
              required: "A password is required.",
              min: 8,
              max: 20
            })}
          ></input>
        </div>

        <div className="input-container">
          <input
            name="passwordConfirmation"
            type="password"
            placeholder="retype password"
            ref={register({
              required: "Please confirm password.",
              validate: {
                matchesPreviousPassword: value => {
                  const { password } = getValues();
                  return password === value || "Passwords should match.";
                }
              }
            })}
          />
        </div>

        <div className="submit-btn-group">
          <div>
            <button type="submit" className="btn btn-register">
              Sign up
            </button>
          </div>
        </div>
        <div className="error-container">
          <p>{errors.password && errors.password.message}</p>
          <p>
            {errors.passwordConfirmation && errors.passwordConfirmation.message}
          </p>
          <p>{errors.email && errors.email.message}</p>
        </div>
      </div>
    </form>
  );
};

export default Register;
