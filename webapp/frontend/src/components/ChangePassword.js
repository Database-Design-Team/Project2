import React from "react";
import { useForm } from "react-hook-form";
import { useHistory } from "react-router-dom";
import axios from "axios";
import "./Register.scss";
import { useStateValue } from "../state";

const ChangePassword = (props) => {
  const [{ credentials }, dispatch] = useStateValue();
  const { register, errors, getValues, handleSubmit } = useForm();
  let history = useHistory();
  const onSubmit = (values) => {
    delete values["passwordConfirmation"];
    console.log(values);

    axios({
      url: "/update-password",
      method: "PUT",
      params: { username: credentials.username, password: values["password"] },
    })
      .then(function(response) {
        console.log(response.data);
        alert("Password sucessfully changed.");
        dispatch({
          type: "changeCredentials",
          postCredentials: {
            username: credentials.username,
            password: values["password"],
          },
        });
      })
      .catch(function(error) {
        console.log(error);
      });
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <div className="login-container">
        <div className="title-container">
          <h1>Change Password</h1>
        </div>

        <div className="input-container">
          <input
            name="password"
            type="password"
            placeholder="••••••••"
            ref={register({
              required: "A password is required.",
              min: 8,
              max: 20,
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
                matchesPreviousPassword: (value) => {
                  const { password } = getValues();
                  return password === value || "Passwords should match.";
                },
              },
            })}
          />
        </div>

        <div className="submit-btn-group">
          <div>
            <button type="submit" className="btn btn-register">
              Confirm Changes
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

export default ChangePassword;
