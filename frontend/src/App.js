import React, { useState } from 'react';
import axios from 'axios';

const App = () => {
  const [id, setId] = useState('');
  const [password, setPassword] = useState('');
  const [name, setName] = useState('');
  const [role, setRole] = useState('');

  const handleSignUp = () => {
    axios
      .post('/sign-api/sign-up', null, {
        params: {
          id: id,
          password: password,
          name: name,
          role: role,
        },
      })
      .then((response) => {
        console.log('회원가입 성공!');
        console.log('회원가입 결과:', response.data);
      })
      .catch((error) => {
        console.error('회원가입 에러:', error);
      });
  };

  return (
    <div>
      <h1>회원가입</h1>
      <input
        type="text"
        placeholder="ID"
        value={id}
        onChange={(e) => setId(e.target.value)}
      />
      <input
        type="password"
        placeholder="비밀번호"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <input
        type="text"
        placeholder="이름"
        value={name}
        onChange={(e) => setName(e.target.value)}
      />
      <input
        type="text"
        placeholder="권한"
        value={role}
        onChange={(e) => setRole(e.target.value)}
      />
      <button onClick={handleSignUp}>회원가입</button>
    </div>
  );
};

export default App;
