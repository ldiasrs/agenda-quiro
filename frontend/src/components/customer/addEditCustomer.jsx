import {NavigationApp} from "../navigation-app";
import {useEffect, useState} from "react";
import Select from 'react-select';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import {ConfirmationModal} from "../ConfirmationModal";
import {ModalFooter} from "../ModalFooter";
import {handleApiGetItem, handleApiSubmit} from "../../services/handlesService";

export const AddEditCustomer = ({ history, match }) => {

    const urlOperation = `/customer`;
    const listRoutePath = '/listarcliente';

    const defaultvalues = {
        id : undefined,
        name: "",
        email : "",
        phone: "",
        cpf: "",
        birthDate : new Date(),
        height : 0,
        weight : 0,
        gender : "male"
    }

    const [name, setName] = useState(defaultvalues.name);
    const [email, setEmail] = useState(defaultvalues.email);
    const [phone, setPhone] = useState(defaultvalues.phone);
    const [cpf, setCpf] = useState(defaultvalues.cpf);
    const [birthDate, setBirthDate] = useState(defaultvalues.birthDate);
    const [height, setHeight] = useState(defaultvalues.height);
    const [weight, setWeight] = useState(defaultvalues.weight);
    const [gender, setGender] = useState(defaultvalues.gender);
    const {id} = match.params;

    // com Async Await
    useEffect(() => {
        async function getItem() {
            const response = await handleApiGetItem(urlOperation, id)
            if (response) {
                setName(response.name)
                setEmail(response.email)
                setPhone(response.phone)
                setCpf(response.cpf)
                setBirthDate(new Date(response.birthDate))
                setHeight(response.height)
                setWeight(response.weight)
                setGender(response.gender)
            }
        }
        getItem();
    }, [match.params]);

    const handleSubmit = async (e) => {
        const formattedDate = birthDate.toISOString().split('T')[0]
        const customerData = {
            name,
            email,
            phone,
            cpf,
            birthDate: formattedDate,
            height,
            weight,
            gender
        }
        await handleApiSubmit(e, history, id, urlOperation, `/customers`, customerData, listRoutePath)
    }

    const genderOptions = [
        { value: 'male', label: 'Masculino' },
        { value: 'female', label: 'Feminino' },
        { value: 'other', label: 'Outro' },
    ]

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
                                <label>Gênero</label>
                                <br></br>
                            </div>
                            <div className="form-group">
                                <br></br>
                                <Select
                                    onChange={handleSelectGenderChange}
                                    options={genderOptions}
                                    value = {
                                        genderOptions.filter(option =>
                                            option.value === gender)
                                    }
                                />
                            </div>
                            <div className="form-group">
                                <label>Data de nascimento</label>
                                <DatePicker dateFormat="dd/MM/yyyy" selected={birthDate} onChange={(date) => setBirthDate(date)} />
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
                        <ModalFooter listRoutePath={listRoutePath} id={id}/>
                    </form>
                </div>
            </div>
            <ConfirmationModal
                history={history}
                deleteApiPath={urlOperation}
                listRoutePath={listRoutePath}
                id={id}
            />
        </>
    )
}
