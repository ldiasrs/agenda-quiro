import { useState } from 'react'
import {login} from "../services/auth";
import {NavigationApp} from "./navigation-app";
import api from "../services/api";

export const Login = (props) => {
    const [username, setUserName] = useState();
    const [password, setPassword] = useState();
    const [error, setError] = useState();

    const handleSubmit = async (e) => {
        e.preventDefault()
        try {
            await api.get("/auth/login", {
                auth: {username: username, password: password
                }
            }).then(response=> login(response.headers.authorization));

            props.history.push("/agenda");
        } catch (err) {
            console.log(err)
            setError("* Houve um problema com o login, verifique suas credenciais.")
        }
    }

    return (
        <>
            <NavigationApp/>
            <div className="container">
                <form className="form-signin" onSubmit={handleSubmit}>
                    <h1 className="h3 mb-3 font-weight-normal">Realize o Login</h1>
                    <label htmlFor="inputEmail" className="sr-only">Email address</label>
                    <input type="email" id="inputEmail" className="form-control" placeholder="Email address" required
                           autoFocus onChange={e => setUserName(e.target.value)}/>
                    <label htmlFor="inputPassword" className="sr-only">Password</label>
                    <input type="password" id="inputPassword" className="form-control" placeholder="Password"
                           required  onChange={e => setPassword(e.target.value)}/>
                    <button className="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
                    <a className="btn btn-link" href="">Esqueci a senha </a>
                    {error && <p class="alert alert-danger">{error}</p>}
                </form>
            </div>
        </>
    )
}
