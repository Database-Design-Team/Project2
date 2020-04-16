import React, { useCallback } from "react";
import axios from "axios";
import { useDropzone } from "react-dropzone";
import "./Upload.scss";

const Upload = (props) => {
  const onDrop = useCallback((acceptedFiles) => {
    const file = acceptedFiles[0];
    const formData = new FormData();
    formData.append("file", file);
    const config = {
      headers: {
        "content-type": "multipart/form-data",
      },
    };
    axios.post("/upload-files", formData, config);
  }, []);

  const { getRootProps, getInputProps, isDragActive } = useDropzone({ onDrop });
  return (
    <div className="upload-container">
      <div {...getRootProps()}>
        <input {...getInputProps()} />
        {isDragActive ? (
          <p>Drop the files here ...</p>
        ) : (
          <p>
            Drag and drop some .mp3 files here, or click here to select files.
          </p>
        )}
      </div>
    </div>
  );
};

export default Upload;
