import { useState } from "react";

function LoginCard() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const onEmailHandler = (event) => {
        setEmail(event.currentTarget.value);
    }
    const onPasswordHandler = (event) => {
        setPassword(event.currentTarget.value);
    }
  return (
    <div>
        <h2>Login</h2>
        <form>
            <input type="radio" name="chk_type" value="psuser" checked="checked"/>병원 회원
            <input type="radio" name="chk_type" value="user"/>일반 회원
            <label>이메일</label>
            <input type='email' value={email} onChange={onEmailHandler}/>
            <label>비밀번호</label>
            <input type='password' value={password} onChange={onPasswordHandler}/>
            <br />
            <button formAction=''>
                로그인
            </button>
        </form>
    </div>
  );
};

export default LoginCard;
