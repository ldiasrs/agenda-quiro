import React, { useState, useEffect } from "react";
import { Navigation } from "./components/navigation";
import { Header } from "./components/header";
import { Features } from "./components/features";
import { About } from "./components/about";
import { Services } from "./components/services";
import { Gallery } from "./components/gallery";
import { Testimonials } from "./components/testimonials";
import { Team } from "./components/Team";
import { Contact } from "./components/contact";
import {Login} from "./components/login";
import JsonData from "./data/data.json";
import SmoothScroll from "smooth-scroll";
import {
    BrowserRouter as Router,
    Switch,
    Route, Redirect,
} from "react-router-dom";
import "./App.css";
import {isAuthenticated} from "./services/auth";
import {ListCustomer} from "./components/customer/listCustomer";
import {AddEditCustomer} from "./components/customer/addEditCustomer";
import {AddEditServiceType} from "./components/servicetype/addEditServiceType";
import {ListServiceType} from "./components/servicetype/listServiceType";
import {ListProfessional} from "./components/professional/listProfessional";
import {AddEditProfessional} from "./components/professional/addEditProfessional";
import {ListCalendar} from "./components/calendar/listCalendar";

const PrivateRoute = ({ component: Component, ...rest }) => (
    <Route
        {...rest}
        render={props =>
            isAuthenticated() ? (
                <Component {...props} />
            ) : (
                <Redirect to={{ pathname: "/login", state: { from: props.location } }} />
            )
        }
    />
);
export const scroll = new SmoothScroll('a[href*="#"]', {
    speed: 1000,
    speedAsDuration: true,
});

const App = () => {
    const [landingPageData, setLandingPageData] = useState({});
    useEffect(() => {
        setLandingPageData(JsonData);
    }, []);

    return (
        <Router>
            <Switch>
                <Route exact path="/login" component={Login} />
                <PrivateRoute exact path="/agenda" component={ListCalendar} key="agenda"/>
                <PrivateRoute exact path="/listarcliente" component={ListCustomer} key="listarcliente"/>
                <PrivateRoute exact path="/cliente/:id" component={AddEditCustomer} />
                <PrivateRoute exact path="/registrarcliente" component={AddEditCustomer} />
                <PrivateRoute exact path="/listarservico" component={ListServiceType} key="listarservico"/>
                <PrivateRoute exact path="/servico/:id" component={AddEditServiceType} />
                <PrivateRoute exact path="/registrarservico" component={AddEditServiceType} />
                <PrivateRoute exact path="/listarprofessional" component={ListProfessional} key="listarprofessional"/>
                <PrivateRoute exact path="/professional/:id" component={AddEditProfessional} />
                <PrivateRoute exact path="/registrarprofessional" component={AddEditProfessional} />

                <Route path="/home">
                    <div>
                        <Navigation />
                        <Header data={landingPageData.Header} />
                        <Features data={landingPageData.Features} />
                        <About data={landingPageData.About} />
                        <Services data={landingPageData.Services} />
                        <Gallery />
                        <Testimonials data={landingPageData.Testimonials} />
                        <Team data={landingPageData.Team} />
                        <Contact data={landingPageData.Contact} />
                    </div>
                </Route>
            </Switch>
        </Router>
    );
};

export default App;
