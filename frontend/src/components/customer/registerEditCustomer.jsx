import {NavigationApp} from "../navigation-app";
import {useState} from "react";
import Select from 'react-select';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

export const RegisterEditCustomer = (props) => {


    const [name, setName] = useState("aaa");
    const [email, setEmail] = useState("");
    const [phone, setPhone] = useState("");
    const [cpf, setCpf] = useState("");
    const [birthDate, setBirthDate] = useState(new Date());
    const [height, setHeight] = useState(0);
    const [weight, setWeight] = useState(0);
    const [gender, setGender] = useState("male");

    const handleSubmit = (e) => {
        e.preventDefault()
        const customerData = {
            name,
            email,
            phone,
            cpf,
            birthDate,
            height,
            weight,
            gender
        }
        console.log(customerData)
    }

    const handleSelectGenderChange = (selectedOption) => {
        setGender(selectedOption.value)
    };

    return (
        <>
            <NavigationApp/>
            <div  className='customForm'>
                <div id="addCustomerModal" className='registerPanel'>
                    <div className="table-title">
                        <div className="row">
                            <div className="col-sm-6">
                                <h2>Cliente</h2>
                            </div>
                        </div>
                    </div>
                    <form onSubmit={handleSubmit}>
                        <div className="modal-body">
                            <div className="form-group">
                                <label>Name</label>
                                <input type="text"
                                       value={name}
                                       onChange={e => setName(e.target.value)}
                                       className="form-control" required/>
                            </div>
                            <div className="form-group">
                                <label>Email</label>
                                <input type="email"
                                       value={email}
                                       onChange={e => setEmail(e.target.value)}
                                       className="form-control" required/>
                            </div>
                            <div className="form-group">
                                <label>Phone</label>
                                <input type="text"
                                       value={phone}
                                       onChange={e => setPhone(e.target.value)}
                                       className="form-control" required/>
                            </div>
                            <div className="form-group">
                                <label>CPF</label>
                                <input type="text"
                                       value={cpf}
                                       onChange={e => setCpf(e.target.value)}
                                       className="form-control" required/>
                            </div>
                            <div >
                                <label>Genero</label>
                                <br></br>
                            </div>
                            <div className="form-group">
                                <br></br>
                                <Select
                                    onChange={handleSelectGenderChange}
                                    options={[
                                        { value: 'male', label: 'Masculino' },
                                        { value: 'female', label: 'Feminino' },
                                        { value: 'other', label: 'Outro' },
                                    ]}
                                />
                            </div>
                            <div className="form-group">
                                <label>Data de nascimento</label>
                                <DatePicker selected={birthDate} onChange={(date) => setBirthDate(date)} />
                            </div>
                            <div className="form-group">
                                <label>Altura</label>
                                <input type="number"
                                       value={height}
                                       onChange={e => setHeight(e.target.value)}
                                       required className="form-control"/>
                            </div>
                            <div className="form-group">
                                <label>Peso</label>
                                <input type="number"
                                       value={weight}
                                       onChange={e => setWeight(e.target.value)}
                                       required className="form-control"/>
                            </div>
                        </div>
                        <div className="modal-footer">
                            <input type="button" className="btn btn-default" data-dismiss="modal" value="Cancelar"/>
                            <input type="submit" className="btn btn-success" value="Salvar"/>
                        </div>
                    </form>
                </div>
            </div>
        </>
    )
}
