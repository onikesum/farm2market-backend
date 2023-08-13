import React from 'react';
import { CreatButton, IndexContainer, InputArea, InputBox, InputCharacter, InputCharacterBox, InputH1, InputKeyword, InputKeywordBox, InputKeywordH2, InputProduct,
   InputProductNameBox, InputProductNameH2, InputSpan,
    InputTrademark,
    InputTrademarkBox,
    InputTrademarkH2,
    OutputArea, OutputContent, OutputTitle, PhotoButton, ResetButton, UploadButton, WritingArea } from './component';

function AIWriting() {
  return (
    <IndexContainer>
      <WritingArea>
        <InputArea>
          <InputH1>
          온라인 등록을 위해 간단한 정보를 입력해주세요!
          <br/>
          <InputSpan>
          구체적인 내용은 인공지능이 대신 작성해줄 거예요.          
          </InputSpan>
          </InputH1>
          <InputBox>
            <InputProductNameBox>
              <InputProductNameH2>상품명</InputProductNameH2>
              <InputProduct/>
            </InputProductNameBox>
            <InputTrademarkBox>
              <InputTrademarkH2>상표명</InputTrademarkH2>
              <InputTrademark/>
            </InputTrademarkBox>
            <InputCharacterBox>
              <InputTrademarkH2>상품 특징</InputTrademarkH2>
              <InputCharacter />
            </InputCharacterBox>
            <InputKeywordBox>
              <InputKeywordH2>키워드</InputKeywordH2>
              <InputKeyword />
            </InputKeywordBox>
          </InputBox>
          <CreatButton>자동 생성</CreatButton>
          <ResetButton>다시 하기</ResetButton>
        </InputArea>
        <OutputArea>
          <PhotoButton>사진 업로드</PhotoButton>
          <OutputTitle />
          <OutputContent />
          <UploadButton>업로드</UploadButton>
        </OutputArea>
      </WritingArea>
    </IndexContainer>
  );
}

export default AIWriting;
