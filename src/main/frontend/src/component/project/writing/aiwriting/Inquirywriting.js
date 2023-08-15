import React from 'react';
import { IndexContainer, InputContent, InputTitle, UploadButton2, WritingBox, } from './component';
import { useSelector } from 'react-redux';
import axios from "axios";

function Inquirywriting() {
  const token = useSelector(state => state.token);
  const responseData = useSelector(state => state.responseData);
  const check = () => {
    console.log('토큰 값:', token);
    console.log('유저 정보:', responseData.token);
  };
  return (
      <IndexContainer>
        <WritingBox>
          <button onClick={check}>테스트1</button>
          <InputTitle placeholder="글 제목" />
          <InputContent placeholder="글 내용"/>
          <UploadButton2>
            업로드
          </UploadButton2>
        </WritingBox>
      </IndexContainer>
  );
}

export default Inquirywriting;
