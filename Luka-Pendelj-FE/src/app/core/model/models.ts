export interface Organization {
  organizationId: number;
  organizationCode?: string;
  active: boolean;
  organizationType: OrganizationType;
  name: string;
  address?: string;
  phone?: string;
  email?: string;
}

export interface OrganizationType {
  orgTypeId: number;
  name: string;
}

//Qualifications:
//[‘Doctor of Medicine’, ‘Medical Assistant’, ‘Nurse Practitioner’, ‘Doctor of Pharmacy’, ‘Certified Nurse Midwife’, ‘Emergency Medical Technician’]

export interface Medic {
  medicId: number;
  medicCode?: string;
  active: boolean;
  firstname: string;
  lastname: string;
  gender: Gender;
  birthDate: Date;
  address?: string;
  phone?: string;
  email?: string;
  qualification: string;
  organization?: Organization;
}

//marital status:
//[‘Annulled’, ‘Divorced’, ‘Married’, ‘Polygamous’, ‘Never Married’, ‘Widowed’]

export interface Patient {
  patientId: 1;
  patientCode?: string;
  active: Boolean;
  firstname: string;
  lastname: string;
  gender: Gender;
  birthDate: Date;
  address?: string;
  phone?: string;
  email?: string;
  deceased?: boolean;
  maritialStatus?: string;
  mainMedic?: Medic;
  organization?: Organization;
}

//[‘asap’, ‘callback results’, ‘emergency’, ‘routine’, ‘rush reporting’, ‘timing critical’]

//[‘planned’, ‘triaged’, ‘in-progress’, ‘suspended’,‘finished’, ‘cancelled’, ’entered-in-error’]
export interface Examination {
  examinationId: number;
  examinationCode?: string;
  status: string;
  serviceType: ServiceType;
  priority?: string;
  startDate?: Date;
  endDate?: Date;
  diagnosis?: string;
  organization?: Organization;
  patient?: Patient;
  medic?: Medic;
}

export interface ServiceType {
  serviceTypeId: number,
  serviceName: string
}

export interface Gender {
  genderId: number,
  genderName: string
}
