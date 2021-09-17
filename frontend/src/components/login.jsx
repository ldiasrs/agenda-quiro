import { useState } from 'react'
import emailjs from 'emailjs-com'

const initialState = {
    name: '',
    email: '',
    message: '',
}
export const Login = (props) => {
    const [{ name, email, message }, setState] = useState(initialState)

    const clearState = () => setState({ ...initialState })
    const [username, setUserName] = useState();
    const [password, setPassword] = useState();
    function loginUser(credentials) {
        return "TOKEN"
        // return fetch('http://localhost:8080/login', {
        //     method: 'POST',
        //     headers: {
        //         'Content-Type': 'application/json'
        //     },
        //     body: JSON.stringify(credentials)
        // })
        //     .then(data => data.json())
    }

    const handleSubmit = (e) => {
        e.preventDefault()
        const token =  loginUser({
            username,
            password
        });
        console.log(`User name: ${username} token: ${token}`)
    }
    return (
        <div className="container">
            <form className="form-signin" onSubmit={handleSubmit}>
                    <h1 className="h3 mb-3 font-weight-normal">Realize o Login</h1>
                    <label htmlFor="inputEmail" className="sr-only">Email address</label>
                    <input type="email" id="inputEmail" className="form-control" placeholder="Email address" required
                           autoFocus onChange={e => setUserName(e.target.value)}/>
                        <label htmlFor="inputPassword" className="sr-only">Password</label>
                        <input type="password" id="inputPassword" className="form-control" placeholder="Password"
                               required/>
                            <button className="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
                        <a className="btn btn-link" href="">Esqueci a senha </a>
            </form>
        </div>
    )
}
